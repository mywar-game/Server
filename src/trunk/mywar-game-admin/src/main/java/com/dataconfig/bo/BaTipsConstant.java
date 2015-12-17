package com.dataconfig.bo;



/**
 * BaTipsConstant entity. @author MyEclipse Persistence Tools
 */

public class BaTipsConstant  implements java.io.Serializable {


    // Fields    

     private Integer tipsConstantId;
     private Integer tipsType;
     private String tipsId;
     private String tipsName;
     private String tipsDesc;
     private Integer wave;


    // Constructors

    /** default constructor */
    public BaTipsConstant() {
    }

    
    /** full constructor */
    public BaTipsConstant(Integer tipsType, String tipsId, String tipsName, String tipsDesc, Integer wave) {
        this.tipsType = tipsType;
        this.tipsId = tipsId;
        this.tipsName = tipsName;
        this.tipsDesc = tipsDesc;
        this.wave = wave;
    }

   
    // Property accessors

    public Integer getTipsConstantId() {
        return this.tipsConstantId;
    }
    
    public void setTipsConstantId(Integer tipsConstantId) {
        this.tipsConstantId = tipsConstantId;
    }

    public Integer getTipsType() {
        return this.tipsType;
    }
    
    public void setTipsType(Integer tipsType) {
        this.tipsType = tipsType;
    }

    public String getTipsId() {
        return this.tipsId;
    }
    
    public void setTipsId(String tipsId) {
        this.tipsId = tipsId;
    }

    public String getTipsName() {
        return this.tipsName;
    }
    
    public void setTipsName(String tipsName) {
        this.tipsName = tipsName;
    }

    public String getTipsDesc() {
        return this.tipsDesc;
    }
    
    public void setTipsDesc(String tipsDesc) {
        this.tipsDesc = tipsDesc;
    }

    public Integer getWave() {
        return this.wave;
    }
    
    public void setWave(Integer wave) {
        this.wave = wave;
    }
   








}