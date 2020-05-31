import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SchoolRoom } from '../../shared/model';
import { SchoolRoomService } from '../../shared/services/school-room.service';

@Injectable()
export class SchoolRoomPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private schoolRoomService: SchoolRoomService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.schoolRoomService.find(id)
                    .subscribe((schoolRoomResponse: HttpResponse<SchoolRoom>) => {
                        const schoolRoom: SchoolRoom = schoolRoomResponse.body;
                        this.ngbModalRef = this.schoolRoomModalRef(component, schoolRoom);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                // setTimeout(() => {
                //     this.ngbModalRef = this.schoolRoomModalRef(component, new SchoolRoom());
                //     resolve(this.ngbModalRef);
                // }, 0);
            }
        });
    }

    schoolRoomModalRef(component: Component, schoolRoom: SchoolRoom): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.schoolRoom = schoolRoom;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
