package geektime.spring.springbucks.jpademo.repository;

import geektime.spring.springbucks.jpademo.model.Coffee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoffeeRepository extends BaseRepository<Coffee, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Tcoffee t set t.name='new latte' where t.id in :ids", nativeQuery = true)
    int updateNameById(@Param(value = "ids") List<Long> ids);

    /**
     * As the EntityManager might contain outdated entities after the execution of the modifying query,
     * we do not automatically clear it (see JavaDoc of EntityManager.clear() for details) since this will effectively drop all non-flushed changes still pending in the EntityManager.
     * If you wish the EntityManager to be cleared automatically you can set @Modifying annotation's clearAutomatically attribute to true.
     * @param id
     * @param newName
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Coffee t set t.name=:newName where t.id=:id")
    int updateNameById(@Param(value = "id") Long id, @Param( value = "newName") String newName);

//    @Modifying
//    @Query("update tcaffee t set t.id = t.id + 1 where t.id =?1")
//    void addElectiveNumByCourseId(Long courseId);

    /**
     * when nativeQuery is true, use table name; otherwise, use entity name as follow
     * @param id
     * @return
     */
    @Query(value = "select * from Tcoffee t where t.id=?1", nativeQuery = true)
    Coffee queryNative(Long id);

    @Query("select t from Coffee t where t.id=?1")
    Coffee query(Long id);

}
