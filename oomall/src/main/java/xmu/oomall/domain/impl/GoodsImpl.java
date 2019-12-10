package xmu.oomall.domain.impl;

import xmu.oomall.domain.Goods;

/**
 * @author zhang
 */
public class GoodsImpl extends Goods {
    public GoodsImpl() {}

    public void setStatus(Boolean status) {
        setStatusCode(status);
    }
    public boolean getStatus() {
        return getStatusCode();
    }

}
