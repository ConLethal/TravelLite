module.exports = {
    collectCoverage: true,
    collectCoverageFrom: ['src/**/*.{jsx}'],
    coverageDirectory: 'coverage',
    testEnvironment: 'jsdom',
    setupFilesAfterEnv: ['<rootDir>/jest.config.js','./jest.setup.js'],
    "moduleNameMapper":{
        "\\.(css|less|scss|sass)$": "identity-obj-proxy"
    }
};