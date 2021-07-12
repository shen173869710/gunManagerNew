package com.auto.di.guan.manager.basemodel.model.respone;
import java.io.Serializable;
/**
 * 种植作物记录表字段
 * @author Administrator
 *
 */
public class RaiseCropsRecord implements Serializable{
	//管水员id
	private Long waterUserId = 2l;
	//成员用户id
	private Long memberUserId = 2l;
	//项目名称
	private String projectName = "项目名称";
	//作物名
	private String cropName = "作物名";
	//品种
	private String varieties = "品种";
	//播种时间
	private Long sowingTime  = System.currentTimeMillis();
	//采收时间
	private Long collectingTime = System.currentTimeMillis();
	//单位产量
	private String outputUnit = "单位产量";
	//年份
	private String outputYm = "年份";
	//拓展字段1
	private String fieldExt1  = "拓展字段1";
	//拓展字段2
	private String fieldExt2  = "拓展字段1";
	//拓展字段3
	private String fieldExt3  = "拓展字段1";
	//拓展字段4
	private String fieldExt4  = "拓展字段1";


	public Long getWaterUserId() {
		return waterUserId;
	}

	public void setWaterUserId(Long waterUserId) {
		this.waterUserId = waterUserId;
	}

	public Long getMemberUserId() {
		return memberUserId;
	}

	public void setMemberUserId(Long memberUserId) {
		this.memberUserId = memberUserId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getVarieties() {
		return varieties;
	}

	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}

	public Long getSowingTime() {
		return sowingTime;
	}

	public void setSowingTime(Long sowingTime) {
		this.sowingTime = sowingTime;
	}

	public Long getCollectingTime() {
		return collectingTime;
	}

	public void setCollectingTime(Long collectingTime) {
		this.collectingTime = collectingTime;
	}

	public String getOutputUnit() {
		return outputUnit;
	}

	public void setOutputUnit(String outputUnit) {
		this.outputUnit = outputUnit;
	}

	public String getOutputYm() {
		return outputYm;
	}

	public void setOutputYm(String outputYm) {
		this.outputYm = outputYm;
	}

	public String getFieldExt1() {
		return fieldExt1;
	}

	public void setFieldExt1(String fieldExt1) {
		this.fieldExt1 = fieldExt1;
	}

	public String getFieldExt2() {
		return fieldExt2;
	}

	public void setFieldExt2(String fieldExt2) {
		this.fieldExt2 = fieldExt2;
	}

	public String getFieldExt3() {
		return fieldExt3;
	}

	public void setFieldExt3(String fieldExt3) {
		this.fieldExt3 = fieldExt3;
	}

	public String getFieldExt4() {
		return fieldExt4;
	}

	public void setFieldExt4(String fieldExt4) {
		this.fieldExt4 = fieldExt4;
	}


}
