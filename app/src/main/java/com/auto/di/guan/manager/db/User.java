package com.auto.di.guan.manager.db;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /** 用户ID */
    private Long userId;
    private Long memberId;
    /** 部门ID */
    private Long deptId;
    /** 部门父ID */
    private Long parentId;
    /** 角色ID */
    private Long roleId;
    /** 登录名称 */
    private String loginName;
    /** 用户名称 */
    private String userName;
    /** 用户类型（00系统用户,01注册用户,02=组长用户,03=管水员用户） */
    private String userType;
    /** 用户邮箱 */
    private String email;
    /** 手机号码 */
    private String phonenumber;
    /** 用户性别 */
    private String sex;
    /** 用户头像 */
    private String avatar;
    /** 密码 */
    private String password;
    /** 盐加密 */
    private String salt;
    /** 帐号状态（0正常 1停用） */
    private int status;

    private String address;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    private String loginIp;
    /** 最后登陆时间 */
    private Date loginDate;
    // mac地址
    private String mac;
    // 项目id
    private String projectId;
    // 项目组id
    private String projectGroupId;
    // 分干管(数量)
    private int trunkPipeNum;
    // 分干管(列)
    private int trunkPipeMaxNum;
    // 出地桩(数量)
    private int pileOutNum;
    // 项目名称
    private String projectName;
    // 项目描述
    private String projectDesc;
    // 项目备注
    private String projectRemarks;
    // 项目经纬度
    private String longitudeLatitude;
    // 项目经纬度
    private String parentLoginName;
    //种植作物
    private String raisecrops;
    //品种
    private String varieties;
    //种植面积
    private String plantingarea;

    private int loginStatus = 1;

    public User() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMemberId() {
        return this.memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getDeptId() {
        return this.deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    public Long getParentId() {
        return this.parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getRoleId() {
        return this.roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserType() {
        return this.userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhonenumber() {
        return this.phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return this.salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDelFlag() {
        return this.delFlag;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    public String getLoginIp() {
        return this.loginIp;
    }
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public Date getLoginDate() {
        return this.loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public String getMac() {
        return this.mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getProjectId() {
        return this.projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getProjectGroupId() {
        return this.projectGroupId;
    }
    public void setProjectGroupId(String projectGroupId) {
        this.projectGroupId = projectGroupId;
    }
    public int getTrunkPipeNum() {
        return this.trunkPipeNum;
    }
    public void setTrunkPipeNum(int trunkPipeNum) {
        this.trunkPipeNum = trunkPipeNum;
    }
    public int getTrunkPipeMaxNum() {
        return this.trunkPipeMaxNum;
    }
    public void setTrunkPipeMaxNum(int trunkPipeMaxNum) {
        this.trunkPipeMaxNum = trunkPipeMaxNum;
    }
    public int getPileOutNum() {
        return this.pileOutNum;
    }
    public void setPileOutNum(int pileOutNum) {
        this.pileOutNum = pileOutNum;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectDesc() {
        return this.projectDesc;
    }
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
    public String getProjectRemarks() {
        return this.projectRemarks;
    }
    public void setProjectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
    }
    public String getLongitudeLatitude() {
        return this.longitudeLatitude;
    }
    public void setLongitudeLatitude(String longitudeLatitude) {
        this.longitudeLatitude = longitudeLatitude;
    }
    public String getParentLoginName() {
        return this.parentLoginName;
    }
    public void setParentLoginName(String parentLoginName) {
        this.parentLoginName = parentLoginName;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getRaisecrops() {
        return raisecrops;
    }

    public void setRaisecrops(String raisecrops) {
        this.raisecrops = raisecrops;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getPlantingarea() {
        return plantingarea;
    }

    public void setPlantingarea(String plantingarea) {
        this.plantingarea = plantingarea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
