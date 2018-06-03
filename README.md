# 中大南方教务系统抢课
原理：构造POST请求，循环刷包。
# 环境要求
jdk 1.8及以上，操作系统不限。
# 使用方法
1.自行下载源码编译运行。<br>
2.下载根目录下的grab.jar，在cmd内执行 java -jar grab.jar运行。
# 配置请求参数
在C盘/用户/公共下新建 data1.txt data2.txt data3.txt等，要抢几门课就建几个。<br>
在里边分别填充需要的参数，运行run方法即可。<br>
如要改请求url或者文件路径，请自行在源码里改。<br>
关于datan.txt内的参数，请参考以下说明。
# 参数说明
1.xh 学号<br>
2.urlxkkh 地址栏get传的那个参数，代表是哪门课<br>
3.VIEWSTATE 记录你的登录状态，和cookie功能一致<br>
4.xkkh上哪门课<br>
5.cookie不多说了<br>
其余都是不变的<br>
如下为一个示例：<br>
cookie::ASP.NET_SessionId=uzhwok55avd3c0qsrjrt4k55
urlxkkh::24184D21F1391C58A99E879FF811F9C24E1CB359372F4CCCF6823BCB2B0D431744EC382C60C49D05
xh::16201000
__EVENTTARGET::Button1<br>
__EVENTARGUMENT:: 
__VIEWSTATE::dDwxMzg2MzMxOTMyO3Q8cDxsPHp5ZG07eG07eHk7ZHFzemo7UkxYWjt4emI7enltYztzZmJqa2M7Y3hibW1zMTtjeGJtbXMyO2h4d3o7PjtsPDIwMTQ75q+b5rOz56WWO+eUteWtkOmAmuS/oeS4jui9r+S7tuW3peeoi+ezuzsyMDE2OzE7MTbpgJrkv6Ez54+tO+mAmuS/oeW3peeoizvlkKY75YWN5L+uO+mHjeS/rjtpPDI+Oz4+O2w8aTwwPjs+O2w8dDw7bDxpPDI+Oz47bDx0PHQ8OztsPGk8MT47Pj47Oz47Pj47Pj47PgQO7LDGhknt0Dh+eUQ3KadO5zwd<br>
xkkh::(2018-2019-1)-105913045-18911-1<br>
RadioButtonList1::0<br><br>
(注意：__EVENTARGUMENT:: 后边是有个空格的）
# 更新
2018-6-3：修复了readline引发的map数据丢失的问题，请放弃上个版本的代码，务必更新到本次。
