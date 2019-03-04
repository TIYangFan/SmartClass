##  项目介绍
    这个项目的主要作用是对服务器处理后的数据在移动端进行可视化，让用户能够更加直观的了解当前课堂内学生的出勤状况和课堂状态等信息和其变化的趋势，从而更加智能地辅助教师进行课堂教学，因此这个项目的主要左右就是对数据的可视化，此项目采用的是 NativeApp 来实现，这样的好处是我们可以获得更加好的用户体验和更加流畅的页面动画效果。
## 注意！作者享有此项目的软件著作权，禁止他人将此项目作为商用或者用于参加比赛等！
## 架构
    MVP 架构
## 项目所涉及的第三方库
    Rxjava2 —— JVM响应式扩展Reactive Extensions 用于使用Java VM的可观察序列编写异步和基于事件的程序的库。
    Retrofit2 + Okhttp3 —— 网络请求框架
    Butterknife —— 视图绑定
    AutoDispose —— 解决RxJava内存泄漏
    MPAndroidChart —— 图表绘制库
## 项目已实现页面
    因为时间的关系，目前所完全实现的页面如下（括号内为其所对应的 MVP 文件命名）：
    登陆（Login）
    个人中心（PersonalCenter）
    当前课堂（CurrentClass）
    当前课堂 ——> 学生状态（StudentStatus）
    当前课堂 ——> 出勤统计（AttendanceStatistics）
    近期记录 ——> 总体（OverallRecentRecord）
    近期记录 ——> 班级（ClassRecentRecord）
