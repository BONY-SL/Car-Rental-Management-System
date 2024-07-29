import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Import Ng-Zorro modules
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzSpinModule } from 'ng-zorro-antd/spin';

@NgModule({
  imports: [
    CommonModule,
    NzButtonModule,
    NzIconModule,
    NzTableModule,
    NzInputModule,
    NzFormModule,
    NzGridModule,
    NzSelectModule,
    NzSpinModule
  ],
  exports: [
    NzButtonModule,
    NzIconModule,
    NzTableModule,
    NzInputModule,
    NzFormModule,
    NzGridModule,
    NzSelectModule,
    NzSpinModule
  ]
})
export class NgZorroImportsModule { }
