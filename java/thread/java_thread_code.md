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