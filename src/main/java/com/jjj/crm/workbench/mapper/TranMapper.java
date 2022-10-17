package com.jjj.crm.workbench.mapper;

import com.jjj.crm.workbench.pojo.Tran;
import com.jjj.crm.workbench.pojo.TranExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int countByExample(TranExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByExample(TranExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insert(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insertSelective(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    List<Tran> selectByExample(TranExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    Tran selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExampleSelective(@Param("record") Tran record, @Param("example") TranExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExample(@Param("record") Tran record, @Param("example") TranExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKeySelective(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKey(Tran record);
}