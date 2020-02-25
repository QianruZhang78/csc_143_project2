package test;

import lib.TestRunner;

public class Project2Evaluation {
    public static void main(String[] args) {
        int totalTests = 37;

        System.out.println("BEGIN PROJECT 2 EVALUATION");
        System.out.println("Total base tests: " + totalTests);
        System.out.println("==========================");
        System.out.println();

        int testsRun = 0;
        int testsPassed = 0;
        boolean allPassed = true;

        TestRunner.TestResults linkedListResult = TestRunner.runTest(LinkedListTest.class);
        System.out.println(linkedListResult.toString());
        allPassed = allPassed && linkedListResult.allPassed();

        testsRun += linkedListResult.testCount;
        testsPassed += linkedListResult.successCount;

        if (testsRun < totalTests) {
            System.out.printf("Note: %d tests were excluded from evaluation.\n", totalTests - testsRun);
        }

        if (testsRun == totalTests && allPassed) {
            System.out.println("!!! ALL LINKED LIST TESTS PASSED! GREAT JOB !!!");
        }

        System.out.printf("Linked List Tests Passed: [%d / %d]\n", testsPassed, testsRun);
        System.out.printf("Base Project Score: %d%%\n", Math.round(Math.floor(((double)(testsPassed) / (double)(totalTests)) * 100)));
    
        // Evaluate bonus section

        int bonusTests = 3;
        int bonusTestsRun = 0;
        int bonusTestsPassed = 0;
        boolean bonusPassed = allPassed;

        System.out.println();
        System.out.println();
        System.out.println("BEGIN P2 BONUS EVALUATION");
        System.out.println("Total bonus tests: " + bonusTests);
        System.out.println("=========================");

        TestRunner.TestResults stackResult = TestRunner.runTest(StackTest.class);
        System.out.println(stackResult.toString());
        bonusPassed = bonusPassed && stackResult.allPassed();        
        
        bonusTestsRun += stackResult.testCount;
        bonusTestsPassed += stackResult.successCount;

        TestRunner.TestResults queueResult = TestRunner.runTest(QueueTest.class);
        System.out.println(queueResult.toString());
        bonusPassed = bonusPassed && queueResult.allPassed();

        bonusTestsRun += queueResult.testCount;
        bonusTestsPassed += queueResult.successCount;

        if (bonusTestsRun < bonusTests) {
            System.out.printf("Note: %d bonus tests were excluded from evaluation.\n", totalTests - testsRun);
        }

        if (testsRun == totalTests && bonusTests == bonusTestsPassed && bonusPassed) {
            System.out.println("!!! ALL PROJECT AND BONUS TESTS PASSED! GREAT JOB !!!");
        }

        System.out.printf("Bonus Tests Passed: [%d / %d]\n", bonusTestsPassed, bonusTestsRun);
        System.out.printf("TOTAL Project Score: %d%%\n",
                Math.round(Math.floor(((double)(testsPassed) / (double)(totalTests)) * 100))
                + Math.round(Math.floor(((double)(bonusTestsPassed) / (double)(bonusTests)) * 15)));
    }
}
