package com.jjj.crm.workbench.mapper;

import com.jjj.crm.workbench.pojo.Activity;
import com.jjj.crm.workbench.pojo.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int countByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int deleteByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    List<Activity> selectByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Oct 02 21:41:33 CST 2022
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Suns Oct 02 21:41:33 CST 2022
     */
    int updateByPrimaryKey(Activity record);

    /**
     * ??????????????????
     * @param condition ??????????????????????????????
     * @return ????????????????????????????????????????????????
     */
    List<Activity> unionSelectByExample(Activity condition);

    /**
     * ??????????????????
     * @param ids ????????????id??????
     * @return ????????????
     */
    int batchDeleteByIds(String[] ids);

    /**
     * ????????????id??????
     * @param ids ????????????????????????
     * @return ????????????????????????
     */
    List<Activity> batchSelectByPrimaryKey(@Nullable String[] ids);

    /**
     * ????????????????????????
     * @param activities ??????????????????
     * @return
     */
    int batchInsertActivities(List<Activity> activities);

    /**
     * ???????????????????????????????????????
     * @param id
     * @return
     */
    Activity selectForDetail(String id);

    /**
     * ??????clueId???????????????????????????
     * @param clueId
     * @return
     */
    List<Activity> selectActivityByClueId(String clueId);

    /**
     * ???????????????????????????????????????
     * @param clueId ??????id
     * @param name ?????????????????????
     * @return
     */
    List<Activity> selectUnboundedActivityByName(@Param("clueId") String clueId, @Param("name") String name);

    /**
     * ????????????????????????????????????
     * @param clueId ??????id
     * @param name ?????????????????????
     * @return
     */
    List<Activity> selectBoundedActivityByName(@Param("clueId") String clueId, @Param("name") String name);
}