import {Directive, DoCheck, ElementRef} from '@angular/core';
import {NgControl} from '@angular/forms';

@Directive({
    selector: '[appNativeValidity]'
})
export class NativeValidityDirective implements DoCheck {

    constructor(private _elemRef: ElementRef, private _control: NgControl) {
    }

    ngDoCheck(): void {
        if (this._control.errors) {
            this._elemRef.nativeElement.setCustomValidity('Error');
        } else {
            this._elemRef.nativeElement.setCustomValidity('');
        }
    }

}
