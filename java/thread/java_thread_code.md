1) VolatileVisibilityDemo : volatile 关键字可见性的演示

2  VolatileAtomicDemo ：volatile 关键字不支持原子性及解决方案的演示。

3) CasDemo : Compare and Swap/Set演示

4）ABADemo： ABA 问题的解决及演示，以及AtomicReference，AtomicStampedReference的使用。

5）CainterNotSafeDemo ：集合类线程不完全的演示ArrayList/Collections.synchronizedList/CopyOnWriteArrayList

6）ReentrantLockDemo:可重入锁的演示

7）SpinLockDemo ：自旋锁的演示

8）ReadWriteLockDemo ：读写锁的演示

9）CountDownLatchDemo ：多线程倒计时的演示

10）CyclicBarrierDemo ：多线程反倒计时的演示

11）SemaphoreDemo ：信号量的控制演示，用于控制共享变量的互斥

12）BlockingQueueDemo ：阻塞队列的实现及演示，阻塞队列用于生产消费者模式/线程池/消息中间件

​		a)线程操纵资源类 b)判断 c)干活 d)唤醒 e)严防多线程环境下的虚假唤醒。

13) ProdConsumer_TraditionalDemo :传统模式利用可重入锁对生产消费者模式（生产一个消费一个）的实现。以及多线程中的判断不能使用if，而要使用while的演示

14）Synchronized和Lock有什么区别？

​		Synchronized是关键字，属于JVM层面，底层是通过汇编monitorenter和moniterexit调用monitor对象实现的。wait和notify的实现也依赖monitor对象。

​		Lock是具体类（java.util.concurrent.locks.Lock）是API层面的锁

​		Synchronized不需要用户取手动释放锁，系统回自动释放

​	    Lock需要用户手动释放，不然容易造成死锁。需要lock/unlock配合try/finally语句块完成。 

​		等待是否可以中断？

​		Synchronized不可以终端，除非抛出异常或正常运行完成。

​		ReentrantLock可中断，a)设置超时方法 trylock(long timeout,TimeUnit unit ) b)lockInterruptibly()放代码块中，调用interrupt（）方法可以中断。

​		加锁是否公平

​		Synchronized是非公平锁

​        ReentrantLock两者皆可，默认非公平锁，构造方法传入bollean值，true为公平锁； false为非公平锁。

​		锁要绑定多个条件的condition

​		Synchronized不支持精确唤醒，要么随机唤醒一个notify，要么全部唤醒notifyAll。

​		ReentrantLock可以创建多个condition condition = lock.newCondition(); 实现精确唤醒condition.await()/condition.signal()/condition.signalAll()

15）ReentrantLockWithMultiConditionDemo : ReentrantLock可以绑定多个conditiona, 实现精确唤醒

16）ProductConsumer_BlockingQueueDemo ：使用blockqueue实现生产消费者模式

17）CallableDemo： 线程runable callable 接口的实现

​	Runnable :线程没有返回值

​	Callable :线程有返回值

18) ThreadPoolDemo : 线程池的使用

​	线程池做的工作主要是控制运行中的线程的数量，处理过程中再将线程放入队列，然后在线程创建后启动这些任务，如果线程的数量超过了最大数量，超出数量的线程排队等候（阻塞队列blockingQueue），等其他线程执行完毕，再从队列中取出任务执行。

主要特点：线程复用；控制最大并发数；管理线程；

第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁的消耗。

第二：提高响应速度。当任务到达时，任务可以不用等到线程创建就可以执行。

第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以统一的分配，调优和监控。

线程池七大参数

1.corePoolSize : 线程中的常驻核心线程数

2.maximumPoolSize:线程池可以容纳的同时执行的最大线程数，此值必须大于等于1

3.keepAvailableTime:多余的空闲线程存活的时间。

​	当前线程数量超过corePoolSize 是，当空闲时间达到keepAvailableTime时，多余空闲线程会被销毁，直到只剩下corePoolSize 个线程为止。

4.unit:keepAvailableTime的时间单位

5.workQueue:任务队列，被提交单尚未执行的任务。（阻塞队列）

6.threadFactory:表示生成线程池中工作线程的线程工厂，用于创建线程，一般用默认的就可以

7.handler :拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）

线程池不允许使用Executers去创建，而是通过ThreadPoolExecutor的方式

线程池配置参数的建议：

​	CPU密集型： 任务需要大量的运算，没有阻塞，CPU一直全速运行，一定是多核CPU. 

​			corePoolSize =CPU核数+1

​			maximumPoolSize=corePoolSize 

   IO密集型：存在大量的阻塞，

​			corePoolSize =CPU核数/(1-阻塞系数) 阻塞系数一般在0.8-.09之间。

​			例CPU核数=8，阻塞系数=0.9 

​			corePoolSize =8/0.9 大约等于80

19）CustomizeThreadPoolDemo：自定义线程池的实现

20）DeadLockDemo ：死锁的实现

​	死锁是指两个或两个以上线程在执行过程中* 因争夺资源而造成的互相等待的现象，若无外力干涉，它们将无法推进下去

​	jps -l :查看java进程command

​	jstack 进程编号 ： list java stack information

​	![1577543478091](C:\Users\PENGLIU\AppData\Roaming\Typora\typora-user-images\1577543478091.png)