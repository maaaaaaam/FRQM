import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

// https://vite.dev/config/
export default defineConfig(
  ({mode}) => {

    const env = loadEnv(mode, path.resolve(__dirname, '..'));

    return {
    test: {globals: true},
    plugins: [react({jsxRuntime: 'classic'})],
    server: {port: env.VITE_FRONTEND_PORT || 5173},
    }

  }

)
