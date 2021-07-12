package com.auto.di.guan.manager.basemodel.model.respone;

import java.io.Serializable;

/**
 * 施肥信息记录表
 * @author Administrator
 */
public class ApplyFertilizerRecord implements Serializable{
	//管水员id
	private Long waterUserId= 22l;
	//成员用户id
	private Long memberUserId = 2l;
	//项目名称
	private String projectName = "项目名称";
	//氮肥名称
	private String nitrogenFertilizerName = "氮肥名称";
	private String nitrogenFertilizerNum= "氮肥数量";
	//磷肥名称
	private String phosphateFertilizerName= "磷肥名称";
	private String phosphateFertilizerNum= "磷肥数量";
	//钾肥名称
	private String potashFertilizerName= "钾肥名称";
	private String potashFertilizerNum= "钾肥数量";
	//复合肥名称
	private String compoundFertilizerName= "复合肥名称";
	private String compoundFertilizerNum= "复合肥数量";
	//其他肥名称
	private String otherFertilizersName= "其他肥名称";
	private String otherFertilizersNum= "其他肥数量";
	//施肥日期
	private Long applyFertilizerDate = System.currentTimeMillis();
	//拓展字段1
	private String fieldExt1 = "拓展字段1";
	//拓展字段2
	private String fieldExt2= "拓展字段2";
	//拓展字段3
	private String fieldExt3= "拓展字段3";
	//拓展字段4
	private String fieldExt4= "拓展字段4";

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

	public String getNitrogenFertilizerName() {
		return nitrogenFertilizerName;
	}

	public void setNitrogenFertilizerName(String nitrogenFertilizerName) {
		this.nitrogenFertilizerName = nitrogenFertilizerName;
	}

	public String getNitrogenFertilizerNum() {
		return nitrogenFertilizerNum;
	}

	public void setNitrogenFertilizerNum(String nitrogenFertilizerNum) {
		this.nitrogenFertilizerNum = nitrogenFertilizerNum;
	}

	public String getPhosphateFertilizerName() {
		return phosphateFertilizerName;
	}

	public void setPhosphateFertilizerName(String phosphate_fertilizer_name) {
		this.phosphateFertilizerName = phosphate_fertilizer_name;
	}

	public String getPhosphateFertilizerNum() {
		return phosphateFertilizerNum;
	}

	public void setPhosphateFertilizerNum(String phosphateFertilizerNum) {
		this.phosphateFertilizerNum = phosphateFertilizerNum;
	}

	public String getPotashFertilizerName() {
		return potashFertilizerName;
	}

	public void setPotashFertilizerName(String potashFertilizerName) {
		this.potashFertilizerName = potashFertilizerName;
	}

	public String getPotashFertilizerNum() {
		return potashFertilizerNum;
	}

	public void setPotashFertilizerNum(String potashFertilizerNum) {
		this.potashFertilizerNum = potashFertilizerNum;
	}

	public String getCompoundFertilizerName() {
		return compoundFertilizerName;
	}

	public void setCompoundFertilizerName(String compoundFertilizerName) {
		this.compoundFertilizerName = compoundFertilizerName;
	}

	public String getCompoundFertilizerNum() {
		return compoundFertilizerNum;
	}

	public void setCompoundFertilizerNum(String compoundFertilizerNum) {
		this.compoundFertilizerNum = compoundFertilizerNum;
	}

	public String getOtherFertilizersName() {
		return otherFertilizersName;
	}

	public void setOtherFertilizersName(String otherFertilizersName) {
		this.otherFertilizersName = otherFertilizersName;
	}

	public String getOtherFertilizersNum() {
		return otherFertilizersNum;
	}

	public void setOtherFertilizersNum(String otherFertilizersNum) {
		this.otherFertilizersNum = otherFertilizersNum;
	}

	public Long getApplyFertilizerDate() {
		return applyFertilizerDate;
	}

	public void setApplyFertilizerDate(Long applyFertilizerDate) {
		this.applyFertilizerDate = applyFertilizerDate;
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
