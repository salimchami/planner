import {Pipe, PipeTransform} from '@angular/core';
import {ArraysHelper} from '../utils/table.helper';

@Pipe({
    name: 'sort'
})
export class ArraySortPipe implements PipeTransform {

    transform(array: any, field: string, direction: string): any[] {
        return ArraysHelper.sortArray(array, field, direction);
    }

}
