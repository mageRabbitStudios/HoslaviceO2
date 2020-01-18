package com.kinzlstanislav.hoslaviceo2.base.viewholder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0007H\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/kinzlstanislav/hoslaviceo2/base/viewholder/ShrinkOnTouchTileViewHolderImpl;", "Lcom/kinzlstanislav/hoslaviceo2/base/viewholder/ShrinkOnTouchTileViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "touchUpAction", "Lkotlin/Function0;", "", "getTouchUpAction", "()Lkotlin/jvm/functions/Function0;", "setTouchUpAction", "(Lkotlin/jvm/functions/Function0;)V", "shrink", "shrinkBack", "Companion", "lib_feature_base_debug"})
public final class ShrinkOnTouchTileViewHolderImpl implements com.kinzlstanislav.hoslaviceo2.base.viewholder.ShrinkOnTouchTileViewHolder {
    
    /**
     * * Very important to use this to assign touch actions to the ViewHolder that delegates this class.
     *     * Since we override onClickListener, the classic addOnClick or addOnTouch listeners in VH wouldn't work.
     *     * 
     */
    @org.jetbrains.annotations.NotNull()
    public kotlin.jvm.functions.Function0<kotlin.Unit> touchUpAction;
    private final android.view.View itemView = null;
    public static final float SHRINK_TO = 0.95F;
    public static final float FULL_SCALE = 1.0F;
    public static final long SHRINK_DURATION = 300L;
    public static final com.kinzlstanislav.hoslaviceo2.base.viewholder.ShrinkOnTouchTileViewHolderImpl.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.jvm.functions.Function0<kotlin.Unit> getTouchUpAction() {
        return null;
    }
    
    @java.lang.Override()
    public void setTouchUpAction(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    public void shrink() {
    }
    
    @java.lang.Override()
    public void shrinkBack() {
    }
    
    public ShrinkOnTouchTileViewHolderImpl(@org.jetbrains.annotations.NotNull()
    android.view.View itemView) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/kinzlstanislav/hoslaviceo2/base/viewholder/ShrinkOnTouchTileViewHolderImpl$Companion;", "", "()V", "FULL_SCALE", "", "SHRINK_DURATION", "", "SHRINK_TO", "lib_feature_base_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}