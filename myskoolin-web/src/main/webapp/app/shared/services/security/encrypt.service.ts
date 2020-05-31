import * as CryptoJS from 'crypto-js';
import {Injectable} from '@angular/core';

@Injectable()
export class EncryptService {
    key: any;
    iv: any;

    constructor() {
        this.key = CryptoJS.enc.Utf8.parse('7061737323313233');
        this.iv = CryptoJS.enc.Utf8.parse('7061737323313233');
    }

    encryptString(item: string) {
        return CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(item), this.key, {
            keySize: 128 / 8,
            iv: this.iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });
    }

    decryptString(item: string) {
        return CryptoJS.AES.decrypt(item, this.key, {
            keySize: 128 / 8,
            iv: this.iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        }).toString(CryptoJS.enc.Utf8);
    }
}
