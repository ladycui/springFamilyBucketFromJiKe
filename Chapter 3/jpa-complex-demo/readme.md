### jpa attention

1. @Query(nativeQuery=true)
    
    means using original sql;
    nativeQuery=false(default) means using entiy name

2. @Modifying(clearAutomatically = true)
    
    default value is false;
    if wanna get updated values, set this annotation true.
    
