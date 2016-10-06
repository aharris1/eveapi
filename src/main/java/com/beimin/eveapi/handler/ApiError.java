package com.beimin.eveapi.handler;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beimin.eveapi.utils.DateUtils;

public class ApiError implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiError.class);
    private static final long serialVersionUID = 137057814306371822L;

    private int code;
    private String error;
    private Date retryAfterDate = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        try {
            int retryIndex = error.indexOf("retry after ");
            if (retryIndex > 0) {
                int beginIndex = retryIndex + 12;
                String substring = error.substring(beginIndex, beginIndex + 19);
                retryAfterDate = DateUtils.getGMTConverter().convert(Date.class, substring);
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Could not parse error properly", e);
            }
        }
    }

    public boolean hasRetryAfterDate() {
        return retryAfterDate != null;
    }

    public Date getRetryAfterDate() {
        return retryAfterDate;
    }

    @Override
    public String toString() {
        return code + ": " + error;
    }
}