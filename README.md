# 中大南方教务系统抢课
原理：构造POST请求，循环刷包。
# 使用方法
执行run方法
# 配置请求参数
在C盘/用户/公共下新建 data1.txt data2.txt data3.txt等，要抢几门课就建几个。<br>
在里边分别填充需要的参数，运行run即可。<br>
如要改请求url或者文件路径，请自行在源码里改。<br>
关于datan.txt内的参数，请参考以下说明。
# 参数说明
1.xh 学号<br>
2.urlxkkh 地址栏get传的那个参数，代表是哪门课<br>
3.VIEWSTATE 记录你的登录状态，和cookie功能一致<br>
4.xkkh上哪门课<br>
5.cookie不多说了<br>
其余都是不变的
# 更新
2018-6-3：修复了readline引发的map数据丢失的问题，请放弃上个版本的代码，务必更新到本次。
