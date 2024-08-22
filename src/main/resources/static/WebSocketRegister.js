import {config} from './config.js';

class WebSocketRegister {
    constructor() {
        this.connections = new Map(); // 存储连接，以页面标识为键
    }

    /**
     * 注册 WebSocket 连接
     * @param {string} pageUrl - 页面标签标识
     * @param {string} token - 用户标识
     * @returns {Promise<void>}
     */
    async register(pageUrl, token) {
        try {
            // 建立 WebSocket 连接
            const wsUrl = `ws://${config.serverUrl}/${pageUrl}`;
            const ws = new WebSocket(wsUrl, [token]);
            ws.onopen = () => {
                this.connections.set(pageUrl, ws);
                console.log(`连接已建立: ${pageUrl}_${token}`);
            };

            ws.onmessage = (event) => {
                console.log(`接收到消息 [${pageUrl}_${token}]:`, event.data);
            };

            ws.onclose = () => {
                console.log(`连接已关闭: ${pageUrl}_${token}`);
                this.connections.delete(pageUrl);
            };

            ws.onerror = (error) => {
                console.error(`连接出错: ${pageUrl}_${token}`, error);
            };
        } catch (error) {
            console.error('注册请求失败:', error);
        }
    }

    /**
     * 注销 WebSocket 连接
     * @param {string} pageUrl - 页面标签标识
     * @param {string} token - 客户端唯一标识
     * @returns {Promise<void>}
     */
    async unregister(pageUrl, token) {
        const ws = this.connections.get(pageUrl);
        if (ws) {
            ws.close();
            this.connections.delete(pageUrl);
        } else {
            console.warn(`连接未找到: ${pageUrl}_${token}`);
        }
    }

    /**
     * 向指定的连接发送消息
     * @param {string} pageUrl - 页面标签标识
     * @param {string} message - 要发送的消息
     */
    sendMessage(pageUrl, message) {
        const ws = this.connections.get(pageUrl);
        if (ws && ws.readyState === WebSocket.OPEN) {
            ws.send(message);
            console.log(`消息已发送到房间 [${pageUrl}]:`, message);
        } else {
            console.warn(`连接未打开或未找到: ${pageUrl}`);
        }
    }
}

// 创建 WebSocketManager 实例
const wsManager = new WebSocketRegister();

// 暴露 API
export const WebSocketAPI = {
    register: (pageUrl, token) => wsManager.register(pageUrl, token),
    unregister: (pageUrl, token) => wsManager.unregister(pageUrl, token),
    sendMessage: (pageUrl, message) => wsManager.sendMessage(pageUrl, message)
};
