package cn.xmkeshe.utils.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author 欣茂科技软件学院（xmkeshe.cn）
 *
 * @param <K> <li>表示要操作的对象主键类型</li>
 * @param <V> <li>表示要操作的对象</li>
 */
public interface IDAO<K, V> {
	/**
	 * <li>增加数据</li>
	 * @param vo 表示要执行增加的数据对象
	 * @return 增加成功返回true、增加失败返回false
	 * @throws SQLException 异常信息返回被调用处处理
	 */
	public boolean doCreate(V vo)throws SQLException;
	/**
	 * <li>对数据行进行更新，更新对时候唯一不变的就是对象的主键</li>
	 * @param vo 表示要更新的对象
	 * @return 更新成功返回true、更新失败返回false
	 * @throws SQLException 异常信息返回被调用处处理
	 */
	public boolean doUpdate(V vo)throws SQLException;
	/**
	 * <li>通过Set集合保存要删除的数据，Set集合保存的数据不会有重复<li/>
	 * @param ids 表示要执行删除的数据集合
	 * @return 删除成功返回true，删除失败返回false
	 * @throws SQLException 异常信息返回被调用处处理
	 */
	public boolean doRemoveBatch(Set<K> ids)throws SQLException;
	/**
	 * <li>通过主键查询数据行中的记录</li>
	 * @param id 表示要执行查询的数据行主键
	 * @return 查询成功返回该数据行中的记录，查询失败返回null。
	 * @throws SQLException 异常信息返回被调用处处理
	 */
	public V findById(K id)throws SQLException;
	/**
	 * <li>查询对象全部数据</li>
	 * @return 查询成功返回全部数据行，如果数据比较多的时候不建议这样操作，可能会导致服务器死机，查询失败返回null
	 * @throws SQLException 异常信息返回被掉调用处处理
	 */
	public List<V> findAll()throws SQLException;
	/**
	 * <li>模糊查询并且实现分页</li>
	 * @param column 表示要模糊查询的字段
	 * @param keyWord 表示要模糊查询的关键字
	 * @param currentPage 表示当前页
	 * @param lineSize 表示每页显示记录数
	 * @return 查询成功返回满足条件的数据行，查询失败返回null
	 * @throws SQLException 异常信息返回被掉调用处处理
	 */
	public List<V> findAllBySplit(String column,String keyWord,Integer currentPage,Integer lineSize)throws SQLException;
	/**
	 * <li>模糊统计数据量</li>
	 * @param column 表示要模糊查询的字段
	 * @param keyWord 表示要模糊查询的关键字
	 * @return 查询成功返回满足条件的数据量，查询失败返回0
	 * @throws SQLException 异常信息返回被掉调用处处理
	 */
	public Integer getAllcount(String column,String keyWord)throws SQLException;
	/**
	 * <li>模糊查询并且实现分页</li>
	 * @param currentPage 表示当前页
	 * @param lineSize 表示每页显示记录数
	 * @return 查询成功返回满足条件的数据行，查询失败返回null
	 * @throws SQLException 异常信息返回被掉调用处处理
	 */
	public List<V> findAllBySplit(Integer currentPage,Integer lineSize)throws SQLException;
	/**
	 * <li>模糊统计数据量</li>
	 * @return 查询成功返回满足条件的数据量，查询失败返回0
	 * @throws SQLException 异常信息返回被掉调用处处理
	 */
	public Integer getAllcount()throws SQLException;
}
