#1. 什么是守护进程
守护进程daemon，是指没有控制终端，运行在后台的进程，通常伴随着系统启动产生，系统关机结束。

#linux:会话.进程组,控制终端
会话：又称为Session，我们正常登录shell之后整个shell程序可以看做一个会话。
进程组：一个会话可以有多个进程组，如果此会话有控制终端，则会有一个前台进程组和若干个后台进程组。
控制终端：一个会话最多只有一个控制终端或者没有，如果ssh登录或者通过命令gnome-terminal打开一个shell,则默认会打开一个终端与此shell对应，在Linux上，通常指的是虚拟终端，也就是/dev/pts/x，可以执行tty命令查看目前shell对应的终端。
原文链接：https://blog.csdn.net/yangbodong22011/article/details/78650896

#