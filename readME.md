
    Purpose (熟悉几项 面试可能用到的知识点)
    
    1. ~Handler 机制~
    2. 观察者模式 => RXJava
    3. ~命令行打包配置~ 
    4. 注解的使用
    5. 事件分发
    6. 自定义View
    7. Activity启动模式
        1. Standard 无需配置默认模式 Activity可以有多个实例 每次启动都会创建新的实例 无论是否已存在该类的实例
        2. SingleTop 类似于标准模式 主要区别在于当一个SingleTopActivity已经位于栈顶时 再去启动时不会再次创建新的实例
        3. SingTask 栈中只能有一个实例 如果当前Activity位于栈顶则不会重新创建 如果不在栈顶 则会使之上的Activity出栈 以重新位于栈顶
        4. singleInstance 栈中只存在一个该Activity的实例 singleInstance模式也是单例的，但和singleTask不同，singleTask只是任务栈内单例，
        系统里是可以有多个singleTask Activity实例的，而singleInstance Activity在整个系统里只有一个实例，启动一singleInstanceActivity时，
        系统会创建一个新的任务栈，并且这个任务栈只有他一个Activity。
    
    note 
    设计模式
        单例
            1. 饿汉式：在程序启动或单件模式类被加载的时候，单件模式实例就已经被创建。（先创建）
                > public class HungryEntity {
                  
                  	private static HungryEntity instance = new HungryEntity();
                  
                  	public static HungryEntity getInstance (){
                  		return instance;
                  	}
                  
                  }
            2. 懒汉式：当程序第一次访问单件模式实例时才进行创建。（即用即建）
                > public class LazyEntity {
                  
                  	private static LazyEntity instace = null;
                  
                  	public LazyEntity getInstace(){
                  		return instace == null? new LazyEntity():instace;
                  	}
                  
                  }
            懒汉模式容易造成不同步的问题所以应该创建同步锁
            
            
    
    Android SQLite简单使用 @See SQLiteActivity
    
    创建：SQLiteOpenHelper的封装，使得我们创建数据库变得异常简单，只需要
    实例化SQLiteOpenHelper子类的对象，接着调用getWritableDatabase()
    或getReadableDatabase()即可得到数据库的引用。
    
[SQLite参考]https://www.jianshu.com/p/3ecb2c7e144e
    
[推送平台]http://sc.ftqq.com/3.version


``` 

四种引用 
强引用(StrongReference) 不会被垃圾回收 对象

弱应用(WeakReference) 垃圾回收时被回收 对象的缓存
软引用(SOftReference)  内存不足时被回收 对象的缓存 
虚引用(PhantomReference)
    虚引用并不会决定对象的生命周期 如果一个对象仅持有虚引用 那么随时可能被垃圾
    回收器回收虚引用主要用来跟踪对象被垃圾回收的器回收的活动 虚引用和弱引用，软引用
    的一个区别在于：虚引用必须和引用队列(ReferenceQueue)联合使用 当垃圾回收器
    准备回收一个对象时 如果发现他还有虚引用 就会在回收对象占有的内存之前 把这个
    虚引用加入到与之关联的引用队列中


```