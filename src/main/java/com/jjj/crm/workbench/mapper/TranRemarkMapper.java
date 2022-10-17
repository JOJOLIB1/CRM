package com.jjj.crm.workbench.mapper;

import com.jjj.crm.workbench.pojo.TranRemark;
import com.jjj.crm.workbench.pojo.TranRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int countByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    List<TranRemark> selectByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExampleSelective(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExample(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKey(TranRemark record);
}