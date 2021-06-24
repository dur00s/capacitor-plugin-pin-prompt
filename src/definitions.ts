export interface PinPromptPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
