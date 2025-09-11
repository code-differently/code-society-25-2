import { NestFactory } from '@nestjs/core';
import { spawn } from 'child_process';
import { AppModule } from './app.module.js';
import { MediaCollectionApp } from './cli/media_collection_app.js';

async function bootstrap() {
  const app = await NestFactory.createApplicationContext(AppModule);
  const cli = app.get(MediaCollectionApp);
  await cli.run();
}
bootstrap();

async function runCliCommand(inputs: string[]): Promise<string> {
  return new Promise((resolve, reject) => {
    const childProcess = spawn(
      'npm',
      ['run', 'start', '--', '--loader', 'danielboyce'],
      {
        stdio: ['pipe', 'pipe', 'pipe'],
        detached: false,
      },
    );

    let output = '';
    let inputIndex = 0;
    let hasProcessed = false;

    if (childProcess.stdout) {
      childProcess.stdout.on('data', (data: Buffer) => {
        const chunk = data.toString();
        output += chunk;

        // Send next input when CLI prompts for it
        if (
          chunk.includes('Enter the number of the desired command:') ||
          chunk.includes('Enter the number of the desired search criteria:') ||
          chunk.includes('Enter the title to search for:') ||
          chunk.includes('Enter the release year to search for:') ||
          chunk.includes('Enter the cast name to search for:')
        ) {
          if (inputIndex < inputs.length && childProcess.stdin) {
            setTimeout(() => {
              if (!hasProcessed) {
                childProcess.stdin?.write(inputs[inputIndex] + '\n');
                inputIndex++;
              }
            }, 100);
          }
        }
      });
    }

    if (childProcess.stderr) {
      childProcess.stderr.on('data', (data: Buffer) => {
        console.error('CLI Error:', data.toString());
      });
    }

    childProcess.on('close', (code) => {
      if (code === 0) {
        resolve(output);
      } else {
        reject(new Error(`CLI process exited with code ${code}`));
      }
    });

    childProcess.on('error', (err) => {
      reject(err);
    });
  });
}

describe('MediaCollectionApp (E2E)', () => {
  it('should run CLI and handle EXIT command', async () => {
    const output = await runCliCommand(['1']); // 1 = EXIT

    expect(output).toContain('Number of items:');
    expect(output).toContain('Enter the number of the desired command:');
  }, 15000);

  it('should handle search by title flow', async () => {
    const output = await runCliCommand([
      '2', // SEARCH
      '1', // Search by title
      'Batman', // Title to search for
      '1', // EXIT
    ]);

    expect(output).toContain('Enter the number of the desired command:');
    expect(output).toContain(
      'Enter the number of the desired search criteria:',
    );
    expect(output).toContain('Enter the title to search for:');
  }, 15000);

  it('should handle search by year flow', async () => {
    const output = await runCliCommand([
      '2', // SEARCH
      '2', // Search by release year
      '2020', // Year to search for
      '1', // EXIT
    ]);

    expect(output).toContain('Enter the number of the desired command:');
    expect(output).toContain(
      'Enter the number of the desired search criteria:',
    );
    expect(output).toContain('Enter the release year to search for:');
  }, 15000);

  it('should handle search by cast flow', async () => {
    const output = await runCliCommand([
      '2', // SEARCH
      '3', // Search by cast
      'Robert Downey Jr.', // Cast member to search for
      '1', // EXIT
    ]);

    expect(output).toContain('Enter the number of the desired command:');
    expect(output).toContain(
      'Enter the number of the desired search criteria:',
    );
    expect(output).toContain('Enter the cast name to search for:');
  }, 15000);
});
