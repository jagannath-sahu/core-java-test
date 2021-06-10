package pattern;

import java.util.Random;

interface Image1 {
     String process();
     void add(Image1 next);
     void wrapAround(Image1 root);
  }

class IR1 implements Image1 {
     Image1 nextInChain;

     public String process() {
         return "IR";
     }

     public void add(Image1 next) {
         if (nextInChain == null) {
             nextInChain = next;
         } else {
             nextInChain.add(next);
         }
     }

     public void wrapAround(Image1 root) {
         if (nextInChain == null) {
             nextInChain = root;
         } else {
             nextInChain.wrapAround(root);
         }
     }
  }

class LS1 implements Image1 {
     Image1 nextInChain;

     public String process() {
         return "LS";
     }

     public void add(Image1 next) {
         if (nextInChain == null) {
             nextInChain = next;
         } else {
             nextInChain.add(next);
         }
     }

     public void wrapAround(Image1 root) {
         if (nextInChain == null) {
             nextInChain = root;
         } else {
             nextInChain.wrapAround(root);
         }
     }

}

class Processor1 {
     private static final Random RANDOM = new Random();
     private static int nextID = 1;
     private int id = nextID++;

     public boolean execute(Image1 img) {
         if (RANDOM.nextInt(2) != 0) {
             System.out.println("   Processor " + id + " is busy");
             if(img instanceof IR1) {
                 execute(((IR1)img).nextInChain);
             }
             if(img instanceof LS1) {
                 execute(((LS1)img).nextInChain);
             }
             return false;
         }
         System.out.println("Processor " + id + " - " + img.process());
         return true;
     }
}

public class ChainDemoProcessor {

     public static void main(String[] args) {
         Image1 rootImg = new IR1();
         rootImg.add(new IR1());
         rootImg.add(new IR1());
         rootImg.add(new LS1());
         rootImg.add(new IR1());
         rootImg.add(new LS1());
         rootImg.add(new LS1());
         rootImg.wrapAround(rootImg);
         Processor1[] processors = {new Processor1(), new Processor1(),
new Processor1()};
         for (int i=0; i < processors.length; i++) {
             processors[i].execute(rootImg);
         }
     }

}