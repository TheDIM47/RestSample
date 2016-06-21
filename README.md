### REST service sample

Write a class that implements a simple calculator RESTful service that caches the results of its computations, with the following endpoints:
```
/add/{a}/{b}/{c}
/subtract/{a}/{b}/{c}
/multiply/{a}/{b}/{c}
/divide/{a}/{b}
```
You may use Jersey / JAX-RS to avoid having to parse JSON manually. Each endpoint should support the GET method, and it should return the result in JSON format. If there is more than one call for the same operation on the same numbers, then the result should be returned from the cache rather than being recomputed.

The calculator calls should support the addition, subtraction, or multiplication of up to three numbers in the same call.