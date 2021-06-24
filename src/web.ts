import { WebPlugin } from '@capacitor/core';

import type { PinPromptPlugin } from './definitions';

export class PinPromptWeb extends WebPlugin implements PinPromptPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
