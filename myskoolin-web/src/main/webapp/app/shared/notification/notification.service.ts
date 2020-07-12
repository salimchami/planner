import {Injectable} from '@angular/core';
// import {ToastData, ToastOptions, ToastyService} from 'ngx-toastr';
import {TranslateService} from '@ngx-translate/core';
import {IndividualConfig, ToastrService} from 'ngx-toastr';
import {NotificationTypes} from '../../shared/notification/notification-types';

@Injectable()
export class NotificationService {
    title: string;
    msg: string;
    theme = 'default';
    type = 'default';

    constructor(private toastr: ToastrService,
                private translateService: TranslateService) {
    }

    add(title: string, msg: string, type: NotificationTypes, closeOthers?: boolean) {
        if (closeOthers) {
            this.toastr.clear();
        }
        this.translateService.get(msg).subscribe((toastMsg) => {
            this.translateService.get(title).subscribe((toastTitle) => {
                const toastOptions: Partial<IndividualConfig> = {
                    tapToDismiss: true,
                    enableHtml: true,
                    closeButton: true,
                    positionClass: 'toast-top-right',
                    timeOut: 10000,
                    extendedTimeOut: 4000,
                };

                switch (type) {
                    case NotificationTypes.INFO:
                        this.toastr.info(toastMsg, toastTitle, toastOptions);
                        break;
                    case NotificationTypes.SUCCESS:
                        this.toastr.success(toastMsg, toastTitle, toastOptions);
                        break;
                    case NotificationTypes.ERROR:
                        this.toastr.error(toastMsg, toastTitle, toastOptions);
                        break;
                    case NotificationTypes.WARNING:
                        this.toastr.warning(toastMsg, toastTitle, toastOptions);
                        break;
                }
            });
        });
    }

}
