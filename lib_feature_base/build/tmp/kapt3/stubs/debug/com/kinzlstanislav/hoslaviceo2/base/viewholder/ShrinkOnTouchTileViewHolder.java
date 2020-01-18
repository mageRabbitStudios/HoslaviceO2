package com.kinzlstanislav.hoslaviceo2.base.viewholder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\t\u001a\u00020\u0004H&J\b\u0010\n\u001a\u00020\u0004H&R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/kinzlstanislav/hoslaviceo2/base/viewholder/ShrinkOnTouchTileViewHolder;", "", "touchUpAction", "Lkotlin/Function0;", "", "getTouchUpAction", "()Lkotlin/jvm/functions/Function0;", "setTouchUpAction", "(Lkotlin/jvm/functions/Function0;)V", "shrink", "shrinkBack", "lib_feature_base_debug"})
public abstract interface ShrinkOnTouchTileViewHolder {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlin.jvm.functions.Function0<kotlin.Unit> getTouchUpAction();
    
    public abstract void setTouchUpAction(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0);
    
    public abstract void shrink();
    
    public abstract void shrinkBack();
}