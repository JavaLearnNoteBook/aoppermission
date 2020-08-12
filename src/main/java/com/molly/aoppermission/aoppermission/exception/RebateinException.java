package com.molly.aoppermission.aoppermission.exception;

public class RebateinException extends  RuntimeException  {
    /**
     * 异常处理
     */
        //自定义错误码
        private Integer code;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

}
