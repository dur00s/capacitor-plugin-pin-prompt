import { registerPlugin } from '@capacitor/core';

import type { PinPromptPlugin } from './definitions';

const PinPrompt = registerPlugin<PinPromptPlugin>('PinPrompt', {
  web: () => import('./web').then(m => new m.PinPromptWeb()),
});

export * from './definitions';
export { PinPrompt };
