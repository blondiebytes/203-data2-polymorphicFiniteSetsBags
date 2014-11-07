Data 2 : Polymorphic Finite Set Bags
===============================
Fall 2014: CS 203

3.3 Polymorphic Finite Bags

Your challenge is to develop a correct, pure implementation of the finite bag data-structure. A finite bag is also called a mulitset and is like a set, but each element may occur many times. Your bags should be polymorphic, i.e. use generics to allow any kind of contents. By "pure", we mean that operations on the set return new sets and do not modify the old set. I expect your sets to be implemented efficiently. Using basic binary search trees is no longer sufficient, instead use a self-balancing binary tree.

You should first design the API for your bags. Please run it by me to make sure you’re on the right track before you dig into the code. You should include some iteration abstraction in your API. For example, the built-in Iterable, or the Sequence or the Fold interfaces discussed in class.

You should thoroughly demonstrate the correctness of these operations by appealing to properties of finite bags. I suggest thinking about how the various operations of the API work together. It may be very useful to look at the Wikipedia page referenced above for a source of ideas of properties.

I highly suggest you use these properties to randomly generate large numbers of test cases and search for witnesses of their negation.

3.3.1 Turn-in and Grading
This project has two pieces to turn in. The first is just like the Finite Sets project:

You should turn in your source code, a console transcript of your test suite’s execution, and an essay which describes your approach to implementing and verifying the system. Your essay should probably make reference to specific lines in your source code and test transcript.

You will be graded on the persuasiveness of your essay.

However, you also need to turn in a manual for your library. The library should document the API, describe its use, explain the properties that hold between API calls, and present performance characteristics. I recommend using a tool like Javadoc to help you make this manual.

You will be graded on the thoroughness of your manual.

These two pieces of the project will be weighted equally.
