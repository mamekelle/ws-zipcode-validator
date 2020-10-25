# ws-zipcode-validator
Williams Sonoma Backend Challenge for Java Developer

# Introduction
The project addresses the acceptance of space separated zip code tuples, 
validations, and merging if exist. So, the size of output<=input.
The operations are:

    - Accept List of paired Zip codes with Minmum and maximum range, validates, 
      if there is overlap merge and outputs in same format in the following format 
       E.g.1   input: [94133,94133] [94200,94299] [94600,94699]
               output: [94133,94133] [94200,94299] [94600,94699]
               
       E.g.2   input: [94133,94133] [94200,94299] [94226,94399]
               output: [94133,94133] [94200,94399]

# Technologies used
Java, Apache Maven, Junit, Log4j, JSR303 validation 
          

# How to run the application
To run the application pass the arguments as follows:

        [94133,94133] [94200,94299] [94600,94699]

N.B. Don't forget to separate by space as any argument to pass to main args
sample image is also present on the repository inside resources/images 
folder. You can also use any of the techniques to pass arguments 
to the "main" method of Java.
 
# Conclusion

By developing this app practiced: Regex pattern, Builder design pattern, Java Reflection API,
Java Bean validation, Java 8 interface new features like (default and static method,
Functional Interfaces), method references, streams,...


If you faced any issue or if you need any clarification please reach me out.
Aman Mohammed


