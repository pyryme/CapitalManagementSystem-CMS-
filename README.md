写一些存在的问题<br>
1.图片上传那里要加一个try，否则有时候axios失败的时候也把图片保存到本地服务器了<br>
2.记得写注释，最后记得删掉不要的注释<br>
3.隐藏的bug，图片的路径有一个斜杠方向不一样<br>
4.更改用户信息那里把图片上传和手机号+用户名上传分开了，因为文件上传贼麻烦<br>
5.bug点：
axios的路径少了项目名称或者任意一点，
数据库的表名写错了，少了一个s，
上传文件的servlet没有配置MultipartConfig，
tomcat7不能直接获取文件名称，要换一种写法，
写在引号""里面的，就算写错了也不报错，比如中英文的？?不一样
pom.xml的servlet配置文件要看tomcat是本地的还是maven插件，provided的要求不一样，
6.经验：照着模板写就能避免很多不必要的bug，比如servlet一开始没有定义userservice这个实例就会报错
7.有些名字vo太长了，之后要学习怎么系统的命名
8.改bug的时候记得保存，否则改不成功就蛋疼了，尽量每一个更改都进行保存
9.后期尽量把if里面加多一个try
10.之后处理 隐藏bug 的标记部分
11.这个获取信息都是基于username的，不是基于id的，之后可以改一改，看看怎么生成系统的id.没有id很难受的，其他系统都是id为核心的
导致这个程序的迁移性不好
没学session吃的亏
12.这个过程的逻辑写好了，但是每次写一次上传或者获取数据库的逻辑函数都花比较久的时间，可以去问问怎么写才能比较快的技巧
13.vue间的method要用this.，
14.
这两种写法有this和不用this的bug
// axios({
//     method: "post",
//     url: "http://localhost:8080/CMS/User/getUserAvatar",
//     data: formData
// }).then(function (resp) {
//     alert("PC.HTML 1get  axios avatar  :"+resp.data)
//     this.user.avatar = resp.data;
//     alert("PC.HTML 2get axios avatar  :"+this.user.avatar);
// }).catch(error => {
//     alert('axios avatar failed. Please try again.');
// });

                axios({
                    method: "post",
                    url: "http://localhost:8080/CMS/User/getUserAvatar",
                    data: formData
                }).then((resp) => {
                    alert("PC.HTML 1get axios avatar: " + resp.data);
                    this.user.avatar = resp.data;
                    alert("PC.HTML 2get axios avatar: " + this.user.avatar);
                }).catch(error => {
                    alert('axios avatar failed. Please try again.');
                });

15.我这里面写的路径是// 完整的文件系统路径
const fullPath = "C:\\GithubResource\\CMS\\src\\main\\webapp\\img\\916e5c67-76f6-451c-bf06-e0acdea71875_laji.jpg";
但是vue加载图片的src只能是不包含C:\\GithubResource\\的路径，记得转换


16.之后留一个日志报错的模板，其他的注释就删掉了
17.有些变量名字前后不一致，比如userPhone 和 PhoneNumber，这样比较难受
而且有些用VO，有些get没有用VO，不知道怎么规范
18.不知道具体的规范，比如我要加一个检验是否是游客的功能就比较麻烦，不知道这个判断的代码放在哪里，哪个层次

19.不知道怎么设计能够像这个DButil一样才能不用一直改动，减少以后增加功能的工作量

20.留存的问题：修改用户信息的时候头像没有改的话会认为修改为空的，这里的逻辑要改

21.项目文件没有引入axios，vue文件，或者
代码没有引入这些资源

22.好像只要写出一个模板，就可以让gpt按照这个模板把整个文件写出来，
爽啊，很爽啊

-------之后的GroupVO要把名字改一改，才能
























