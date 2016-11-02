#import <Cordova/CDVPlugin.h>
#import <objc/runtime.h>

@interface ResponsiveWebView : CDVPlugin <UIScrollViewDelegate> {
    @protected
    id _keyboardShowObserver, _keyboardHideObserver;
    IMP wkOriginalImp, uiOriginalImp, nilImp;
    Method wkMethod, uiMethod;
}

@property (readwrite, assign) BOOL hideKeyboardAccessoryBar;
@property (readwrite, assign) BOOL disableScroll;
//@property (readwrite, assign) BOOL styleDark;

@end
