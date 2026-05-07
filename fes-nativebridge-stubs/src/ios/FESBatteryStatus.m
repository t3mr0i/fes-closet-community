/**
 * FESBatteryStatus.m
 * Stub for Sony FES NativeBridge BatteryStatus on iOS.
 * Returns phone battery level (0-100) via UIDevice.
 */

#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"
#import <UIKit/UIKit.h>

@interface FESBatteryStatus : CDPGate
@end

@implementation FESBatteryStatus

- (void)getBatteryLevel:(CDPMethodContext*)context
{
    [UIDevice currentDevice].batteryMonitoringEnabled = YES;
    float level = [UIDevice currentDevice].batteryLevel;
    NSInteger percent = level < 0 ? 100 : (NSInteger)(level * 100);
    [self resolveParams:context withParams:@[@(percent)]];
}

@end
