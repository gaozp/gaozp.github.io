---
layout: post
title: 高通应用启动优化IO-Prefetcher源码解析(二)
categories: [tech]
---

上次说到了是如何缓存到本地的，那么缓存到本地就应该使用了吧。在start_capture对应的就是start_playback

```cpp
193void start_playback(char *pkg_name_arg) {
...
216    if(pthread_create(&start_playback_pthread_id, NULL, start_playback_thread, pkg_name)) {
217         return ;
218    }
219    QLOGI("Exit playback");
220    return ;
221}
```

创建一个线程来进行操作

```cpp
56void* start_playback_thread(void *pkg_name_arg)
57{
58    file_details *file_detail_ptr;
59    int num_file = 0;
60    int num_of_files = 0;
61    char *pkg_name = (char *)pkg_name_arg;
62    int i = 0;
63    int total_data = 0;
64    struct stat file_stat;
65    ATRACE_BEGIN("start_playback_thread: Enter");
66
67    QLOGI("pkg_name = %s",pkg_name);
68    num_file = get_total_file(pkg_name);
69
70    if( num_file <= 0)
71    {
72        QLOGI("no file to read get_total_file %d", num_file);
73        remove_pkg(pkg_name);
74        free(pkg_name);
75        ATRACE_END();
76        return NULL;
77    }
78
79    file_detail_ptr = (file_details *) malloc(sizeof(file_details) * num_file);
80    if (NULL == file_detail_ptr)
81    {
82        QLOGI("Fail to allocate memory");
83        remove_pkg(pkg_name);
84        free(pkg_name);
85        ATRACE_END();
86        return NULL;
87    }
88
89    num_of_files = get_file_list(pkg_name,file_detail_ptr,num_file);
90    QLOGI("num_of_files = %d",num_of_files);
91
92    if (num_of_files <= 0)
93    {
94        QLOGI("no file to read get_file_list");
95        remove_pkg(pkg_name);
96        free(file_detail_ptr);
97        free(pkg_name);
98        ATRACE_END();
99        return NULL;
100    }
101
102    if( num_file > MAX_NUM_FILE)
103    {
104        num_file = MAX_NUM_FILE;
105    }
106
107    for(i = 0; i < num_file;i++)
108    {
109        static unsigned int  c_int = 0;
110        int fd = -1;
111        int num_pages = 0;
112        int page_ittr = 0;
113        char trace_buf[4096];
114        char *file_mmap = NULL;
115
116        snprintf(trace_buf,4096,"Opening file %s",file_detail_ptr[i].file_name);
117        ATRACE_BEGIN(trace_buf);
118        fd = open(file_detail_ptr[i].file_name, O_RDONLY);
119        if(fd == -1)
120        {
121            mark_for_delete(pkg_name,file_detail_ptr[i].file_name);
122            ATRACE_END();
123            continue;
124        }
125        if ( fstat( fd, &file_stat ) < 0 )
126        {
127            QLOGI("fail to get file stat");
128            mark_for_delete(pkg_name,file_detail_ptr[i].file_name);
129            close(fd);
130            ATRACE_END();
131            continue;// goto  next file
132        }
133        if ( file_stat.st_size == 0 )
134        {
135            // File zie is zero
136            mark_for_delete(pkg_name,file_detail_ptr[i].file_name);
137            close(fd);
138            ATRACE_END();
139            continue;// goto  next file
140        }
141        file_mmap = (char *)mmap(NULL, file_stat.st_size, PROT_READ
142                                , MAP_SHARED| MAP_NORESERVE, fd, 0 );
143        if ( file_mmap == MAP_FAILED )
144        {
145            QLOGI("Fail to map the file");
146            mark_for_delete(pkg_name,file_detail_ptr[i].file_name);
147            close(fd);
148            ATRACE_END();
149            continue;// goto  next file
150        }
151
152        snprintf(trace_buf,4096,"reading file %s size = %d",file_detail_ptr[i].file_name
153                                ,(int)file_stat.st_size);
154        ATRACE_BEGIN(trace_buf);
155        num_pages = (file_stat.st_size + PAGE_SIZE - 1)/ PAGE_SIZE;
156        QLOGI("%d. mapping file %s pages = %d size = %d ",i,file_detail_ptr[i].file_name
157                                ,num_pages,file_stat.st_size);
158        for(page_ittr = 0; page_ittr < num_pages ; page_ittr++)
159        {
160
161            c_int+= file_mmap[page_ittr*PAGE_SIZE];
162
163            total_data += PAGE_SIZE;
164            if(page_ittr*PAGE_SIZE > MAX_FILE_SIZE)
165            {
166                QLOGI("Max file size limit reached");
167                //Stop reading as reach on max file limit
168                break;
169            }
170        }
171
172        if (munmap(file_mmap, file_stat.st_size)) QLOGI("munmap failed");
173        ATRACE_END();
174        ATRACE_END();
175
176        close(fd);
177        if(total_data > MAX_TOTAL_DATA)
178        {
179            QLOGI("Max total size limit reached Total = %d",total_data);
180            //STOP reading and max total reached
181            break;
182        }
183    }
184
185
186    ATRACE_END();
187    QLOGI(" Total Data = %d",total_data);
188    free(pkg_name);
189    free(file_detail_ptr);
190    return NULL;
191}
```

那这块看源码就能知道，从数据库中查询到对应的文件，然后进行打开操作。那么问题来了。

是在什么地方文件的capture和playback呢？