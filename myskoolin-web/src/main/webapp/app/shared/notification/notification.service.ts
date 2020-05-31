import {Injectable} from '@angular/core';
// import {ToastData, ToastOptions, ToastyService} from 'ngx-toastr';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class NotificationService {
    title: string;
    msg: string;
    theme = 'default';
    type = 'default';

    constructor(// private toastyService: ToastyService,
        private translateService: TranslateService) {
    }

    /**
     *
     * @param title toast title
     * @param msg toast message
     * @param type 'error', 'wait', 'success', 'default', 'info', 'warning'
     * </code>
     * @param closeOthers : close others toasts
     */
    addToast(title: string, msg: string, type: string, closeOthers?: boolean) {
        if (closeOthers) {
            // this.toastyService.clearAll();
        }
        this.translateService.get(msg).subscribe((toastMsg) => {
            this.translateService.get(title).subscribe((toastTitle) => {
                // const toastOptions: ToastOptions = {
                //     msg: toastMsg,
                //     title: toastTitle,
                //     showClose: true,
                //     timeout: 8000,
                //     theme: 'default',
                //     onAdd: (toast: ToastData) => {
                //         /* added */
                //     },
                //     onRemove: (toast: ToastData) => {
                //         /* removed */
                //     }
                // };
                //
                // switch (type) {
                //     case 'default':
                //         this.toastyService.default(toastOptions);
                //         break;
                //     case 'info':
                //         this.toastyService.info(toastOptions);
                //         break;
                //     case 'success':
                //         this.toastyService.success(toastOptions);
                //         break;
                //     case 'wait':
                //         this.toastyService.wait(toastOptions);
                //         break;
                //     case 'error':
                //         this.toastyService.error(toastOptions);
                //         break;
                //     case 'warning':
                //         this.toastyService.warning(toastOptions);
                //         break;
                // }
            });
        });
    }

}
