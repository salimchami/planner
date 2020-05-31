import {Directive, HostListener} from '@angular/core';

import * as screenfull from 'screenfull';

@Directive({
  selector: '[jhiToggleFullScreen]'
})
export class ToggleFullScreenDirective {

  @HostListener('click')
  onClick() {
    if (screenfull.enabled) {
      screenfull.toggle();
    }
  }
}
