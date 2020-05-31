import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'jhi-footer',
    templateUrl: './footer.component.html'
})
export class FooterComponent implements OnInit {
    year: number;

    ngOnInit(): void {
        this.year = new Date().getFullYear();
    }
}
