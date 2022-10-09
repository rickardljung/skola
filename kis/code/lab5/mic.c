#include <mb_interface.h>
#include <xutil.h>
#include <stdio.h>
#include <time.h>
#include "xuartlite_l.h"
#include "xparameters.h"
#include "xtmrctr.h"

#define TMRCTR_DEVICE_ID        XPAR_TMRCTR_0_DEVICE_ID
#define TIMER_COUNTER_0         0

unsigned getnum()
{
        char srb=0;
        unsigned num=0;

        // skip non digits
        while(srb < '0' || srb > '9') srb=XUartLite_RecvByte(STDIN_BASEADDRESS);

        // read all digits
        while(srb >= '0' && srb <= '9') {
                num=num*10+(srb-'0');
                srb=XUartLite_RecvByte(STDIN_BASEADDRESS);
        };
        return num;
}

int main(){
        u32 start;
        u32 end;
        XTmrCtr TImerCounter;
        XTmrCtr* TmrCtrInstancePtr = &TImerCounter;
        int Status = XTmrCtr_Initialize(TmrCtrInstancePtr, TMRCTR_DEVICE_ID);
                if (Status != XST_SUCCESS) {
                return XST_FAILURE;
        }
        XTmrCtr_SetOptions(TmrCtrInstancePtr, TIMER_COUNTER_0,
                                XTC_AUTO_RELOAD_OPTION);

        xil_printf("Insert the number of integers\n\r");
        int n = getnum();
        xil_printf("Insert the integers\n\r");
        int numbers[n];
        int i;

        for (i = 0; i < n; ++i) {
                numbers[i] = getnum();
        }

        start =  XTmrCtr_GetValue(TmrCtrInstancePtr, TIMER_COUNTER_0);
        XTmrCtr_Start(TmrCtrInstancePtr, TIMER_COUNTER_0);

        int divisor = numbers[0];
        for(i = 1; i < n; i++) {
        	putfsl(numbers[i], 0);
        	putfsl(divisor, 0);
        	getfsl(divisor, 0);
        }
        end =  XTmrCtr_GetValue(TmrCtrInstancePtr, TIMER_COUNTER_0);

        xil_printf("Result: "); //Detta va det sista vi gjorde när vi ville se effekten av att ha utskrifter i tidtagningen. Det har vi beskrivit i vår rapport! See 3.1 i rapporten (preformance)
       xil_printf("%d \n\r", divisor);



       xil_printf("%d \n\r", end - start);

       return 0;

}


