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

23.数据库的数据类型和DAO定义的不一样

24.抄别人的代码不一定是好事，不知道对方的数据类型转换思路，
自己先用思维导图将整体的推导再来写效率才高，而且也不会痛苦
别人的代码只是看看思路，了解核心本质就自己写


25.
<label for="direction">Group Direction:</label>
<textarea id="direction" v-model="direction"@input="checkDirLength"  placeholder="Enter group Direction"></textarea>
<p v-if="exceededLimit_Dir" style="color: red;">Exceeded character limit!</p>
这里的for和id要和new Vue里面的一样，否则就不能联系

26.sql语言中groups是关键词，要用反引号，不同于单引号，我也不知道怎么打出来的

27.总结：可以进行信息传递到下一个网页的方法，
方法1：直接在html之间传递，就是<a :href="'groupDetails.html?id=' + group.id">
方法2：在服务端进行传递，用session或者cookie


28.写一个功能要先想好输入输出，然后代码从外写到内，从内写到外这个顺序

29.写完之后要总结经验

30.像思维导图这种东西十分重要，不写不行


31.后端将ArrayList<String>类型的数据展示到vue框架上的时候，以下是一个模板，
  <!-- 我加入的群组 -->
<h2>我加入的群组</h2>
  <div class="group-list">
    <div v-for="group in joinedGroups" :key="group" class="group-card">
      <a :href="'groupDetails.html?id=' + group">
        <h3>{{ group }}</h3>
      </a>
    </div>
  </div>

  <!-- 我创建的群组 -->
<h2>我创建的群组</h2>
  <div class="group-list">
    <div v-for="group in createdGroups" :key="group" class="group-card">
      <a :href="'groupDetails.html?id=' + group">
        <h3>{{ group }}</h3>
      </a>
    </div>
  </div>
</div>

<script>
  new Vue({
    el: '#app',
    data() {
      return {
        joinedGroups: [],
        createdGroups: []
      };
    },




32.自己的命名不规范，在数据库用了is_Public类似的短杠,但是在前面的servlet的地方也用这种短杠result_isPublic，不太好


33.前端传信息到servlet
        const params = new URLSearchParams();
        params.append("groupName", this.groupName);
        params.append("groupScale", this.groupScale);
        params.append("direction", this.direction);
        params.append("isPublic", this.isPublic);

        //
        // const formData = new FormData();
        // formData.append("groupName",this.groupName );
        // formData.append("groupScale",this.groupScale );
        // formData.append("direction",this.direction );
        // formData.append("isPublic",this.isPublic );

        axios({
          method: "post",
          url: "http://localhost:8080/CMS/Group/createGroup",
          data: params

后端：
//设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");





        //那个VO作为service的参数没有卵用，还麻烦有限制。
        //id放最后dao处理吧
        String groupName = req.getParameter("groupName");
        String groupScale = req.getParameter("groupScale");
        String direction = req.getParameter("direction");
        String isPublic = req.getParameter("isPublic");

有两个传递的比较重要，一个是页面之间，一个是页面到servlet


34.放在result的Object data中有3种返回类型
    1.是普通的常见类型，string等（这个没有键值对,并且声明的时候就能完成空间申请，不用new）
    2.是一个类的一组信息，比如group_id，group_name等（这个有键值对，并且声明的同时要用new申请空间）
    3.是数组，可以是常见类型的string的数组，也可以类的数组(这个要用new ArrayList<>()来申请空间)

35.从servlet传送一个类的一组信息到前端的写法看看UnJoinGroupDetail.html处理方式




36.最后的工作就是改成中文，去掉无效提醒信息，去掉无用注释就可以了



