package com.yuanbw.lanshan.voicenote.dao;

public interface CodeDao {
    //查询到数据库中，code的id值最大的那个id，便于为之后生成的二维码编号
    public int findMaxCodeID();

    //生成二维码的同时，将编号信息插入数据库中,只插入id信息，src为null
    public int insertCodeInfo(int codeID);
}

