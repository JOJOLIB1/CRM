package com.jjj.crm.workbench.mapper;

import com.jjj.crm.workbench.pojo.ContactsActivityRelation;
import com.jjj.crm.workbench.pojo.ContactsActivityRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactsActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int countByExample(ContactsActivityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByExample(ContactsActivityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insert(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int insertSelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    List<ContactsActivityRelation> selectByExample(ContactsActivityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    ContactsActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExampleSelective(@Param("record") ContactsActivityRelation record, @Param("example") ContactsActivityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByExample(@Param("record") ContactsActivityRelation record, @Param("example") ContactsActivityRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKeySelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Mon Oct 17 20:48:21 CST 2022
     */
    int updateByPrimaryKey(ContactsActivityRelation record);

    /**
     * ??????List????????????????????????
     */
    int insertByList(List<ContactsActivityRelation> contactsActivityRelationList);
}