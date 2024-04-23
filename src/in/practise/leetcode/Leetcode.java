package in.practise.leetcode;

import sun.awt.Mutex;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode() {
      }

      TreeNode(int val) {
          this.val = val;
      }

      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


  // https://leetcode.com/problems/construct-quad-tree/

   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class FooBar {
    private int n;

    private Mutex fooLock ;
    private Mutex barLock ;

    public FooBar(int n) {
        this.n = n;
       fooLock = new Mutex();
       barLock = new Mutex();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
           barLock.lock();
            printFoo.run();
           barLock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

           fooLock.lock();
            printBar.run();
            fooLock.unlock();
        }
    }
}
