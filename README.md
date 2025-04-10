1. Basic Thread Creation Create two threads that print "Thread 1" and "Thread 2" respectively 10 times
    each. Use both the Thread class and the Runnable interface to implement this.
 2. Producer-Consumer Problem
    Implement the classic Producer-Consumer Problem using wait() and notify(). Create a shared buFer
    (e.g., List<Integer>) and have one thread produce items (add to the list) while another consumes them
    (removes from the list).
 3. Thread Synchronization Write a program where multiple threads are incrementing a shared counter.
    Ensure that the counter is thread-safe using synchronized blocks or ReentrantLock.
 4. Deadlock Simulation Create a scenario where two threads are causing a deadlock by both trying to
    lock two shared resources in opposite order. Then, try to resolve the deadlock using either a timeout
    or by ensuring resources are locked in a specific order.
5. Read-Write Locks Simulate a scenario where multiple threads are reading from a shared resource
   (e.g., a list), but only one thread can modify it at a time. Use a ReentrantReadWriteLock to manage
   concurrent reads and exclusive writes.