### jpa attention

1. @Query(nativeQuery=true)
    
    means using original sql;
    nativeQuery=false(default) means using entiy name

2. @Modifying(clearAutomatically = true)
    
    default value is false;
    if wanna get updated values, set this annotation true.
    
-----------
### Validation

1. 单个参数校验 vs bean校验
2. exception handler
3. fast fail
4. group